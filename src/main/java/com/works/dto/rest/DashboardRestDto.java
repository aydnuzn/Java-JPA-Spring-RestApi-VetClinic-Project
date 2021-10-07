package com.works.dto.rest;

import com.works.entities.projections.DashboardInfo;
import com.works.entities.projections.DiaryToDashboard;
import com.works.repositories._jpa.DashboardRepository;
import com.works.repositories._jpa.UserFollowInRepository;
import com.works.repositories._jpa.UserRepository;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class DashboardRestDto {

    final UserFollowInRepository userFollowInRepository;
    final UserRepository userRepository;
    final DashboardRepository dashboardRepository;

    public DashboardRestDto(UserFollowInRepository userFollowInRepository, UserRepository userRepository, DashboardRepository dashboardRepository) {
        this.userFollowInRepository = userFollowInRepository;
        this.userRepository = userRepository;
        this.dashboardRepository = dashboardRepository;
    }

    public Map<Object, Object> dashboard() {
        Map<Object, Object> hm = new LinkedHashMap<>();
        Optional<DashboardInfo> optionalDashboardInfo = dashboardRepository.getViewDashboard();
        if (optionalDashboardInfo.isPresent()) {
            Float rateDiary = 0.0f;
            Float rateCustomer = 0.0f;
            Float rateCiro = 0.0f;
            Integer diaryToday = 0;
            Integer diaryYesterday = 0;
            Integer registercustomerToday = 0;
            Integer registercustomerYesterday = 0;
            Integer todayCiro = 0;
            Integer yesterdayCiro = 0;
            Integer stockValue = 0;
            try {
                diaryToday = Integer.parseInt(optionalDashboardInfo.get().getDiaryToday());
                diaryYesterday = Integer.parseInt(optionalDashboardInfo.get().getDiaryYesterday());
                registercustomerToday = Integer.parseInt(optionalDashboardInfo.get().getRegisterCustomerToday());
                registercustomerYesterday = Integer.parseInt(optionalDashboardInfo.get().getRegisterCustomerYesterday());
                todayCiro = Integer.parseInt(optionalDashboardInfo.get().getTodayCiro());
                yesterdayCiro = Integer.parseInt(optionalDashboardInfo.get().getYesterdayCiro());
                stockValue = Integer.parseInt(optionalDashboardInfo.get().getStockValue());
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Değer dönüşüm hatası: " + e);
                return hm;
            }
            if (diaryYesterday != 0) {
                rateDiary = ((diaryToday - diaryYesterday) / (float) diaryYesterday);
                rateDiary = rateDiary * 100;
            }
            if (registercustomerYesterday != 0) {
                rateCustomer = ((registercustomerToday - registercustomerYesterday) / (float) registercustomerYesterday);
                rateCustomer = rateCustomer * 100;
            }
            if (yesterdayCiro != 0) {
                rateCiro = ((todayCiro - yesterdayCiro) / (float) yesterdayCiro);
                rateCiro = rateCiro * 100;
            }

            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Kontrol paneli bilgileri başarıyla bulundu.");

            hm.put("rateDiary", rateDiary);
            hm.put("rateCustomer", rateCustomer);
            hm.put("rateCiro", rateCiro);
            hm.put("diaryToday", diaryToday);
            hm.put("customerToday", registercustomerToday);
            hm.put("todayCiro", todayCiro);
            hm.put("stockValue", stockValue);

            // Bugunku tarihi gecen randevular
            List<DiaryToDashboard> dashExpiredDiaryList = dashboardRepository.getExpiredDiary();
            hm.put("dashExpiredDiaryList", dashExpiredDiaryList);

            // Bugunku yaklasan randevular
            List<DiaryToDashboard> dashUpcomingDiaryList = dashboardRepository.getUpcomingDiary();
            hm.put("dashUpcomingDiaryList", dashUpcomingDiaryList);
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Kontrol paneli bilgileri bulunamadı.");
        }
        return hm;
    }

    public Map<REnum, Object> getCustomerRegisterDayOfWeeks() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<String> ls = new ArrayList<>();
        Object[] registerCustomerDaysOfWeek = dashboardRepository.registerCustomerDaysOfWeek();
        for (int i = 0; i < 7; i++) {
            ls.add(String.valueOf(((BigInteger) (((Object[]) registerCustomerDaysOfWeek[0])[i])).intValue()));
        }
        if (ls.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İşlem başarılı.");
            hm.put(REnum.COUNT, ls.size());
            hm.put(REnum.RESULT, ls);
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız.");
        }
        return hm;
    }

    public Map<String, Object> getPetTypeCount() {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            List<Object[]> petTypeNumber = dashboardRepository.petTypeCount();
            double sum = 0;
            for (int i = 0; i < petTypeNumber.size(); i++) {
                sum += ((BigInteger) (((Object[]) petTypeNumber.get(i))[1])).doubleValue();
            }
            hm.put("STATUS", true);
            hm.put("MESSAGE", "İşlem Başarılı.");
            for (int i = 0; i < petTypeNumber.size(); i++) {
                Double temp = ((BigInteger) (((Object[]) petTypeNumber.get(i))[1])).doubleValue();
                Double temp2 = (temp * 100) / sum;
                Formatter formatter = new Formatter();
                formatter.format("%.2f", temp2);
                String temp3 = formatter.toString().replace(',', '.');
                Double newValue = Double.parseDouble(temp3);
                hm.put(String.valueOf(((((Object[]) petTypeNumber.get(i))[0]))), newValue);
            }
        } catch (Exception ex) {
            hm.put("STATUS", false);
            hm.put("MESSAGE", "İşlem başarısız.");
        }
        return hm;
    }

}
