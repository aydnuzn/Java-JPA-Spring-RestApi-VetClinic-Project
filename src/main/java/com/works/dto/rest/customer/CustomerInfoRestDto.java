package com.works.dto.rest.customer;

import com.works.entities.constant.pets.*;
import com.works.entities.projections.AllCustomerPetInfo;
import com.works.properties.UpdatePetInterlayer;
import com.works.repositories._jpa.*;
import com.works.restcontroller.ProfileInfoRestController;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerInfoRestDto {
    final PetRepository petRepository;
    final ProfileInfoRestController profileInfoRestController;
    final JoinPetCustomerRepository joinPetCustomerRepository;
    final TypePetRepository typePetRepository;
    final ColorPetRepository colorPetRepository;
    final BreedPetRepository breedPetRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;
    final LabRepository labRepository;

    public CustomerInfoRestDto(PetRepository petRepository, ProfileInfoRestController profileInfoRestController, JoinPetCustomerRepository joinPetCustomerRepository, TypePetRepository typePetRepository, ColorPetRepository colorPetRepository, BreedPetRepository breedPetRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository, LabRepository labRepository) {
        this.petRepository = petRepository;
        this.profileInfoRestController = profileInfoRestController;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
        this.typePetRepository = typePetRepository;
        this.colorPetRepository = colorPetRepository;
        this.breedPetRepository = breedPetRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
        this.labRepository = labRepository;
    }

    public Map<REnum, Object> customerInfo(String stCid) {
        Integer cid = 0;
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            cid = Integer.parseInt(stCid);
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "M????teri bulunamad??. - Integer ifade girilmesi gerekir.");
            return hm;
        }

        List<AllCustomerPetInfo> allCustomerPetInfoList = petRepository.getCustomerPets(cid);

        //M????teri ekran?? a????labilmesi i??in, en az 1 pete sahip olmal??.
        if (allCustomerPetInfoList == null || allCustomerPetInfoList.size() == 0) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "M????teri ve pet e??le??meleri bulunamad??. - Aran??lan m????teriye ait pet bilgisi yok.");
            return hm;
        }

        hm.put(REnum.STATUS, true);
        hm.put(REnum.MESSAGE, "Arama ba??ar??l??.");
        hm.put(REnum.COUNT, allCustomerPetInfoList.size());
        hm.put(REnum.RESULT, allCustomerPetInfoList);
        return hm;
    }

    public Map<REnum, Object> petDelete(String index) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Integer petId = Integer.parseInt(index);
            Optional<JoinPetCustomer> optPetInfo = joinPetCustomerRepository.findById(petId);
            if (optPetInfo.isPresent()) {
                Integer s1 = labRepository.isLabResult(optPetInfo.get().getJpt_id());
                if (s1 == 0) {
                    joinPetCustomerRepository.deleteById(optPetInfo.get().getJpt_id());
                    Pet pet = optPetInfo.get().getPet();
                    JoinTypeBreedPet joinPetId = pet.getJoinTypeBreedPet();
                    petRepository.delete(pet);
                    joinTypeBreedPetRepository.delete(joinPetId);
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "Silme ba??ar??l??.");
                    hm.put(REnum.RESULT, "Silinen Pet : " + pet);
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Silinme i??lemi iptal edildi. Ba??l?? tablolar silinemez. (Foreign Key)");
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri taban??nda" + petId + " numaral?? pet bulunamad??.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Pet bulunamad??. - Integer ifade girilmesi gerekir.");
        }
        return hm;
    }

    public Map<REnum, Object> getPetInfo(String index) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Integer petId = Integer.parseInt(index);
            Optional<Pet> optPet = petRepository.findById(petId);
            if (optPet.isPresent()) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "????lem ba??ar??l??.");
                hm.put(REnum.RESULT, optPet.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri taban??nda" + petId + " numaral?? pet bulunamad??.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Pet bulunamad??. - Integer ifade girilmesi gerekir.");
        }
        return hm;
    }

    public Map<REnum, Object> updatePet(UpdatePetInterlayer item, BindingResult bindingResults) {
        System.out.println("dene");
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResults.hasErrors()) {
            try {
                Pet pet = new Pet();

                pet.setPet_name(item.getName());
                pet.setPet_chipNumber(item.getChipNumber());
                pet.setPet_earTag(item.getEarTag());

                pet.setPet_bornDate(item.getBornDate());

                if (item.getNeutering().equals("true")) {
                    pet.setPet_neutering(true);
                } else {
                    pet.setPet_neutering(false);
                }

                if (item.getGender().equals("true")) {
                    pet.setPet_gender(true);
                } else {
                    pet.setPet_gender(false);
                }

                //Color
                Integer color_id = 0;
                if (!item.getColor().equals("0")) {
                    try {
                        color_id = Integer.parseInt(item.getColor());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Color Casting Error!");
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Renk Se??ilmedi.");
                    return hm;
                }

                Optional<ColorPet> optColor_pet = colorPetRepository.findById(color_id);
                if (optColor_pet.isPresent()) {
                    pet.setColorPet(optColor_pet.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri taban??nda" + item.getColor() + " numaral?? renk bulunamad??.");
                    return hm;
                }

                //Irk T??r Nesnesi olu??turma
                JoinTypeBreedPet joinTypeBreedPet = new JoinTypeBreedPet();

                //Type
                Integer type_id = 0;
                if (!item.getType().equals("0")) {
                    try {
                        type_id = Integer.parseInt(item.getType());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Type Casting Error");
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Tip Se??ilmedi");
                    return hm;
                }

                Optional<TypePet> optType = typePetRepository.findById(type_id);
                if (optType.isPresent()) {
                    joinTypeBreedPet.setTypePet(optType.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri taban??nda" + item.getType() + " numaral?? t??r bulunamad??.");
                    return hm;
                }

                //Breed
                Integer breed_id = 0;
                if (!item.getBreed().equals("0")) {
                    try {
                        breed_id = Integer.parseInt(item.getBreed());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Breed Casting Error");
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Irk Se??imi Yap??lmad??");
                    return hm;
                }

                // Pet Irk,Cins Change
                Optional<BreedPet> optBreed = breedPetRepository.findById(breed_id);
                if (optBreed.isPresent()) {
                    if (optBreed.get().getType_pet_id() != optType.get().getTy_id()) {
                        //Girilen Irk numaras?? T??re ait mi?
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Se??ilen Irk tipi - giri?? yap??lan t??re ait de??il.");
                        return hm;
                    }
                    joinTypeBreedPet.setBreedPet(optBreed.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri taban??nda" + item.getBreed() + " numaral?? ??rk mevcut de??ildir.");
                    return hm;
                }
                joinTypeBreedPet.setJtbp_id(item.getJtbpId());
                joinTypeBreedPet = joinTypeBreedPetRepository.saveAndFlush(joinTypeBreedPet);
                pet.setJoinTypeBreedPet(joinTypeBreedPet);
                pet.setPet_id(item.getPetId());
                petRepository.saveAndFlush(pet);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "????lem ba??ar??l??.");
                hm.put(REnum.RESULT, pet);
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "????lem Ba??ar??s??z");
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Do??rulama(Validasyon) Hatas??. Girilen de??erlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResults));

        }
        return hm;
    }

}
