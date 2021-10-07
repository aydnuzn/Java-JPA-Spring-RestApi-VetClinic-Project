package com.works.dto.rest;

import com.works.entities.Customer;
import com.works.entities.Diary;
import com.works.entities.calender.CalendarInfo;
import com.works.entities.calender.ScheduleCalendar;
import com.works.entities.projections.DiaryInfo;
import com.works.entities.security.User;
import com.works.properties.DiaryInterlayer;
import com.works.repositories._jpa.CustomerRepository;
import com.works.repositories._jpa.DiaryRepository;
import com.works.repositories._jpa.UserRepository;
import com.works.repositories._jpa.calender.CalendarInfoRepository;
import com.works.repositories._jpa.calender.ScheduleCalendarRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DiaryRestDto {

    final DiaryRepository diaryRepository;
    final UserRepository userRepository;
    final CustomerRepository customerRepository;
    final CalendarInfoRepository calendarInfoRepository;
    final ScheduleCalendarRepository scheduleCalendarRepository;

    public DiaryRestDto(DiaryRepository diaryRepository, UserRepository userRepository, CustomerRepository customerRepository, CalendarInfoRepository calendarInfoRepository, ScheduleCalendarRepository scheduleCalendarRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.calendarInfoRepository = calendarInfoRepository;
        this.scheduleCalendarRepository = scheduleCalendarRepository;
    }

    public Map<REnum,Object> addInsert(DiaryInterlayer diaryInterlayer, BindingResult bindingResult) {
        Map<REnum,Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            Diary diary = new Diary();

            Integer us_id = diaryInterlayer.getUs_id();
            Optional<User> optUser = userRepository.findById(us_id);
            if (optUser.isPresent()) {
                diary.setUser(optUser.get());
            }else{
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + us_id + " numaraları kullanıcı bulunamadı.");
                return hm;
            }

            Integer cu_id = diaryInterlayer.getCu_id();
            Optional<Customer> optCustomer = customerRepository.findById(cu_id);
            if (optCustomer.isPresent()) {
                diary.setCustomer(optCustomer.get());
            }else{
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + cu_id + " numaraları müşteri bulunamadı.");
                return hm;
            }

            diary.setDiary_title(diaryInterlayer.getDiary_title());
            diary.setDiary_description(diaryInterlayer.getDiary_description());
            diary.setDiary_date(diaryInterlayer.getDiary_date());
            diary.setDiary_time(diaryInterlayer.getDiary_time());

            if (diaryInterlayer.getDiary_statu() != null) {
                diary.setDiary_statu(true);
            } else {
                diary.setDiary_statu(false);
            }

            //DENEME
            //Hasta takviminin bilgilerini alma.
            String bg_color = "";
            String border_color = "";
            String color = "";
            String drag_bg_color = "";
            Optional<CalendarInfo> o = calendarInfoRepository.findById(1);
            if (o.isPresent()) {
                bg_color = o.get().getCbgColor();
                border_color = o.get().getCborderColor();
                color = o.get().getCcolor();
                drag_bg_color = o.get().getCdragBgColor();
            }
            else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + 1 + " numaraları takvim bilgisi bulunamadı.");
                return hm;
            }
            Integer calendar_id = 1;
            String category = "time";
            //Format ayarlanacak
            String time = diaryInterlayer.getDiary_date() + " " + diaryInterlayer.getDiary_time();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                date = formatter.parse(time);
            } catch (ParseException e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "ParseException: " + e );
                return hm;
            }


            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(date);               // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 1);      // adds one hour
            Date finishTime = cal.getTime();

            System.out.println("BAŞLANGIÇ ZAMANI : " + date);
            System.out.println("BİTİŞ ZAMANI : " + finishTime);

            //FORMAT DEĞİŞİKLİĞİ DEVAMI
            //Sat Sep 25 19:44:00 EET 2021 - BİZDE OLAN
            //Fri Sep 24 2021 19:44:00 GMT+0300 - İSTENEN

            String[] d = date.toString().split(" ");
            String newDate = d[0] + " " + d[1] + " " + d[2] + " " + d[5] + " " + d[3] + " GMT+0300";

            String[] d2 = finishTime.toString().split(" ");
            String newDate2 = d2[0] + " " + d2[1] + " " + d2[2] + " " + d2[5] + " " + d2[3] + " GMT+0300";


            String id = UUID.randomUUID().toString();
            Boolean is_all_day = false;
            String title = optUser.get().getUs_name();
            String location = optCustomer.get().getCu_name();
            String raw = "public";
            String state = "Free";

            ScheduleCalendar sc = new ScheduleCalendar();
            sc.setBgColor(bg_color);
            sc.setBorderColor(border_color);
            sc.setColor(color);
            sc.setDragBgColor(drag_bg_color);
            sc.setCalendarId(calendar_id.toString());
            sc.setCategory(category);
            sc.setStart(newDate);
            sc.setEnd(newDate2);
            sc.setId(id);
            sc.setIsAllDay(is_all_day);
            sc.setTitle(title);
            sc.setLocation(location);
            sc.setRaw(raw);
            sc.setState(state);
            sc.setDueDateClass("");
            try {
                scheduleCalendarRepository.save(sc);
                diaryRepository.save(diary);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı!");
                hm.put(REnum.RESULT, diary);
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Exception: "+e);
            }
        } else{
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
       return hm;
    }


    public  Map<REnum, Object>getDiaryList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Randevu Listesi başarıyla geri döndürüldü.");
            hm.put(REnum.COUNT, diaryRepository.count());
            hm.put(REnum.RESULT, diaryRepository.allDiaryList());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "getDiaryList sırasında bir hata oluştu: " + e);
        }
        return hm;
    }

    public  Map<REnum, Object> getDiaryListSearch(String search) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<DiaryInfo> allDiaryListSearch = new ArrayList<>();
        try {
            allDiaryListSearch = diaryRepository.allDiaryListSearch(search.trim());
            if(allDiaryListSearch.size()>0){
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Randevu Arama başarıyla geri döndürüldü.");
                hm.put(REnum.COUNT, allDiaryListSearch.size());
                hm.put(REnum.RESULT, allDiaryListSearch);
            }else{
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "getDiaryListSearch sırasında bir hata oluştu: " + e);
        }
        return hm;
    }

    public Map<REnum, Object> delete(String stDiary_id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidProduct = false;
        try {
            Integer diaryId = Integer.parseInt(stDiary_id);
            isValidProduct = diaryRepository.existsById(diaryId);
            if (isValidProduct) {
                diaryRepository.deleteById(diaryId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Randevu başarıyla silindi");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen Randevu mevcut değil");
            }
        } catch (DataIntegrityViolationException ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız. Silinmek istenen Randevu başka tablo tarafından kullanılmaktadır.");
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Randevu Delete casting hatası");
        }
        hm.put(REnum.RESULT, stDiary_id);
        return hm;
    }


}
