package com.works.dto.rest;

import com.works.entities.projections.DiaryInfoToIstaticticPageCustomer;
import com.works.entities.projections.DiaryInfoToIstatisticPage;
import com.works.repositories._jpa.DiaryRepository;
import com.works.repositories._jpa.UserFollowInRepository;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StatisticRestDto {

    final DiaryRepository diaryRepository;
    final UserFollowInRepository userFollowInRepository;

    public StatisticRestDto(DiaryRepository diaryRepository, UserFollowInRepository userFollowInRepository) {
        this.diaryRepository = diaryRepository;
        this.userFollowInRepository = userFollowInRepository;
    }

    public Map<Object, Object> statistic() {
        Map<Object, Object> hm = new LinkedHashMap<>();
        Optional<DiaryInfoToIstatisticPage> opt = diaryRepository.findBusiestDayOfWeek();
        Optional<DiaryInfoToIstaticticPageCustomer> opt2 = diaryRepository.findBusiestCustomer();
        Integer randevuNumber = 0;
        String date = "";
        if (opt.isPresent()) {
            date = opt.get().getDaysOfWeek();
            if (date == null) {
                date = "Pazartesi";
            }
            if (date.equals("1")) {
                date = "Pazartesi";
            } else if (date.equals("2")) {
                date = "Salı";
            } else if (date.equals("3")) {
                date = "Çarşamba";
            } else if (date.equals("4")) {
                date = "Perşembe";
            } else if (date.equals("5")) {
                date = "Cuma";
            } else if (date.equals("6")) {
                date = "Cumartesi";
            } else {
                date = "Pazar";
            }
            try {
                randevuNumber = Integer.parseInt(opt.get().getDiaryNumber());
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.ERROR, "StatisticController Error");
            }
        }
        Integer randevuNumber2 = 0;
        String cuname = "";
        if (opt2.isPresent()) {
            try {
                randevuNumber2 = Integer.parseInt(opt2.get().getCount_diary());
                cuname = opt2.get().getCu_name();
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.ERROR, "StatisticController Error");
            }
        }
        Optional<String> optional = userFollowInRepository.getNumber();
        Integer ziyaretsayisi = 0;
        if (optional.isPresent()) {
            try {
                ziyaretsayisi = Integer.parseInt(optional.get());
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.ERROR, "StatisticController Error");
            }
        }

        List<Object[]> customerList = userFollowInRepository.ls();//Bu Hafta Hangi Müşteri Ne Kadar Kazandırdı
        List<Object[]> saleTypeList = userFollowInRepository.ls2();//Ödeme Tipine Göre Kazanç

        saleTypeList.forEach(item -> {
            if (item[0].equals(1)) {
                item[0] = "Nakit";
            } else if (item[0].equals(2)) {
                item[0] = "Kredi";
            } else {
                item[0] = "Havale";
            }
        });

        hm.put(REnum.STATUS, true);

        hm.put(REnum.MESSAGE, "İstatistik bilgileri başarıyla eklenmiştir.");
        hm.put("enyogungun", date);
        hm.put("randevusayisi", randevuNumber);
        hm.put("customername", cuname);
        hm.put("count_diary", randevuNumber2);
        hm.put("ziyaretsayisi", ziyaretsayisi);
        hm.put("customerList", customerList);
        hm.put("saleTypeList", saleTypeList);

        return hm;
    }
}