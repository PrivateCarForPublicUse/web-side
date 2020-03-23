package com.training.service.Impl;

import com.training.Util.EntityConstructor;
import com.training.domain.Reimburse;
import com.training.domain.Route;
import com.training.domain.Settlement;
import com.training.domain.User;
import com.training.dto.*;
import com.training.model.ReimburseListOfUser;
import com.training.model.ReimburseModel;
import com.training.repository.*;
import com.training.response.ResponseResult;
import com.training.service.ReimburseService;
import com.training.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * by Huang
 */
@Service
public class ReimburseServiceImpl implements ReimburseService {
    @Autowired
    RouteService routeService;
    @Autowired
    ReimburseRepository reimburseRepository;
    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    SecRouteRepository secRouteRepository;

    @Autowired
    UserRepository userRepository;
//    @Override
//    public ResponseResult getReimburses(Long companyId) {
//        List<Reimburse> reimburses = reimburseRepository.findAllByCompany(companyId);
//        if (reimburses.size() == 0)
//            return new ResponseResult(500, "不存在任何记录!");
//        return new ResponseResult(reimburses);
//    }

    @Override
    public ResponseResult getReimbursesByStatus(int isReimburse) {
        List<Reimburse> reimburses = reimburseRepository.findByStatus(isReimburse);
        if (reimburses.size() == 0) {
            switch (isReimburse) {
                case 0:
                    return new ResponseResult(501, "没有未报销的行程!");
                case 1:
                    return new ResponseResult(502, "没有已报销的行程!");
                case -1:
                    return new ResponseResult(503, "没有报销失败的行程!");
                case 2:
                    return new ResponseResult(509, "没有待审核的行程!");
                default:
                    return new ResponseResult(504, "无效的识别码!");
            }
        }
        return new ResponseResult(reimburses);
    }

    @Override
    public ResponseResult getReimburseById(Long id) {
        try {
            Reimburse reimburse = reimburseRepository.findById(id).get();
            return new ResponseResult(reimburse);
        } catch (Exception e) {
            return new ResponseResult(505, "id不存在!");
        }
    }

    @Override
    public ResponseResult getReimburseByRouteId(Long routeId) {
        Reimburse reimburse = reimburseRepository.findByRouteId(routeId);
        if (reimburse == null)
            return new ResponseResult(506, "routeId不存在!");
        return new ResponseResult(reimburse);
    }

    @Override
    public ResponseResult save(Reimburse reimburse) {
        try {
            Reimburse r = reimburseRepository.save(reimburse);
            return new ResponseResult(r);
        } catch (Exception e) {
            return new ResponseResult(508, "新建失败!");
        }
    }

    @Override
    public ResponseResult update(Reimburse reimburse) {
        try {
            Reimburse r = reimburseRepository.save(reimburse);
            return new ResponseResult(r);
        } catch (Exception e) {
            return new ResponseResult(507, "更新失败!");
        }
    }

    @Override
    public ResponseResult applyReimburse(List<Long> routeIds) {
        try {
            for (Long routeId : routeIds) {
                Reimburse reimburse = reimburseRepository.findById(routeId).get();
                reimburse.setIsReimburse(2);
                reimburseRepository.save(reimburse);
            }
            return new ResponseResult(200, "更新成功!");
        } catch (Exception e) {
            return new ResponseResult(507, "更新失败!");
        }
    }
    //上面的方法暂时都不要了

    @Override
    public ResponseResult getReimburse(Long userId) {
        ResponseResult routeByUserId = routeService.findRouteByUserId(userId);
        List<Route> list = (List<Route>) routeByUserId.getData();
        List<ReimburseListOfUser> dtoList = new ArrayList<>();
        for (Route route : list) {
            ReimburseListOfUser reimburseListOfUser = new ReimburseListOfUser();
            this.searchStartAndEndPlaceByRouteId(route.getId(), reimburseListOfUser);
        }
        return null;
    }

    //一些小修改
    @Override
    public ResponseResult GetReimburseStatistic(Long companyId) {
        Map<String, List<Route>> temp = new HashMap<>();
        List<Route> reject = routeRepository.findRoutesByIsReimburseAndCompanyId(-1, companyId);
        List<Route> accepted = routeRepository.findRoutesByIsReimburseAndCompanyId(1, companyId);
        List<Route> reimbursing = routeRepository.findRoutesByIsReimburseAndCompanyId(2, companyId);

        for (Route route : reject) {
            String time = route.getHandleTime().substring(0, 10);
            if(temp.keySet().contains(time)){
                temp.get(time).add(route);
            }
            else{
                List<Route> list = new LinkedList<>();
                list.add(route);
                temp.put(time,list);
            }
        }

        for (Route route : accepted) {
            String time = route.getHandleTime().substring(0, 10);
            if(temp.keySet().contains(time)){
                temp.get(time).add(route);
            }
            else{
                List<Route> list = new LinkedList<>();
                list.add(route);
                temp.put(time,list);
            }
        }

        for (Route route : reimbursing) {
            String time = route.getReimburseTime().substring(0, 10);
            if(temp.keySet().contains(time)){
                temp.get(time).add(route);
            }
            else{
                List<Route> list = new LinkedList<>();
                list.add(route);
                temp.put(time,list);
            }
        }

        Set<String> keys = temp.keySet();

        List<ReimburseStatisticByDay> list = new LinkedList<>();
        double sum=0;int time=0;
        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
            String keyStr = (String) iter.next();
            ReimburseStatisticByDay day = new ReimburseStatisticByDay();
            day.setDate(keyStr);
            ReimburseDetail is1 = new ReimburseDetail();
            ReimburseDetail isfu1 = new ReimburseDetail();
            ReimburseDetail is2= new ReimburseDetail();
            for (Route route : temp.get(keyStr)) {
                if(route.getIsReimburse()==-1){
                    isfu1.setMoney(isfu1.getMoney()+route.getPrice());
                    isfu1.setTime(isfu1.getTime()+1);
                }
                if(route.getIsReimburse()==1){
                    is1.setMoney(is1.getMoney()+route.getPrice());
                    is1.setTime(is1.getTime()+1);
                }
                if(route.getIsReimburse()==2){
                    is2.setMoney(is2.getMoney()+route.getPrice());
                    is2.setTime(is2.getTime()+1);
                }
            }
            sum+=is1.getMoney();
            time+=is1.getTime();
            day.setRejected(isfu1);
            day.setImbursed(is1);
            day.setIsimbursing(is2);
            list.add(day);
        }

        Collections.sort(list, Comparator.comparing(ReimburseStatisticByDay::getDate));

        ReimburseStatistic ans = new ReimburseStatistic();
        ans.setDataOfEveryDay(list);
        ReimburseDetail summ = new ReimburseDetail();
        summ.setTime(time);
        summ.setMoney(sum);
        ans.setSum(summ);

        return new ResponseResult(ans);
    }

    @Override
    public ResponseResult findUserIdAndSumPrice(Long companyId){
        List<Object[]> userIdAndSumPrice = routeRepository.findUserIdAndSumPrice(companyId);
        List ans = null;
        try {
            ans = EntityConstructor.castEntity(userIdAndSumPrice,UserIdAndSumPrice.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserIdAndSumPrice userIdAndSumPrice1=(UserIdAndSumPrice)ans;
        User user = userRepository.findUserById(userIdAndSumPrice1.getUserId().longValue());

        return new ResponseResult(new UserAndSumPrice(user,userIdAndSumPrice1.getSum()));
    }

    @Override
    public ResponseResult findUserIdAndSumTimes(Long companyId) {
        List<Object[]> userIdAndSumPrice = routeRepository.findUserIdAndSumTimes(companyId);
        List ans = null;
        try {
            ans = EntityConstructor.castEntity(userIdAndSumPrice, UserIdAndSumTimes.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserIdAndSumTimes userIdAndSumTimes=(UserIdAndSumTimes) ans;
        User user = userRepository.findUserById(userIdAndSumTimes.getUserId().longValue());

        return new ResponseResult(new UserAndSumTimes(user,userIdAndSumTimes.getTimes()));
    }

    @Override
    public ResponseResult GetReimburseStatistic_person(Long id) {
        Map<String, List<Route>> temp = new HashMap<>();
        List<Route> reimburseofperson = routeRepository.findRouteByUserId(id);
        for (Route route : reimburseofperson) {
            if (route.getIsReimburse()==1||route.getIsReimburse()==-1) {
                String time = route.getHandleTime().substring(0, 10);
                if (temp.keySet().contains(time)) {
                    temp.get(time).add(route);
                } else {
                    List<Route> list = new LinkedList<>();
                    list.add(route);
                    temp.put(time, list);
                }
            }else if (route.getIsReimburse()==2){
                String time = route.getReimburseTime().substring(0, 10);
                if(temp.keySet().contains(time)){
                    temp.get(time).add(route);
                }
                else {
                    List<Route> list = new LinkedList<>();
                    list.add(route);
                    temp.put(time, list);
                }
            }

        }

        Set<String> keys = temp.keySet();

        List<ReimburseStatisticByDay> list = new LinkedList<>();
        double sum=0;int time=0;
        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
            String keyStr = (String) iter.next();
            ReimburseStatisticByDay day = new ReimburseStatisticByDay();
            day.setDate(keyStr);
            ReimburseDetail is2 = new ReimburseDetail();
            ReimburseDetail is1 = new ReimburseDetail();
            ReimburseDetail isfu1 = new ReimburseDetail();
            for (Route route : temp.get(keyStr)) {
                if(route.getIsReimburse()==-1){
                    isfu1.setMoney(isfu1.getMoney()+route.getPrice());
                    isfu1.setTime(isfu1.getTime()+1);
                }
                if(route.getIsReimburse()==2){
                    is2.setMoney(is2.getMoney()+route.getPrice());
                    is2.setTime(is2.getTime()+1);
                }
                if(route.getIsReimburse()==1){
                    is1.setMoney(is1.getMoney()+route.getPrice());
                    is1.setTime(is1.getTime()+1);
                }
            }
            sum+=is1.getMoney();
            time+=is1.getTime();
            day.setRejected(isfu1);
            day.setImbursed(is1);
            day.setIsimbursing(is2);
            list.add(day);
        }
        ReimburseStatistic ans = new ReimburseStatistic();
        ans.setDataOfEveryDay(list);
        ReimburseDetail summ = new ReimburseDetail();
        summ.setTime(time);
        summ.setMoney(sum);
        ans.setSum(summ);

        return new ResponseResult(ans);
    }

    private void searchStartAndEndPlaceByRouteId(Long id, ReimburseListOfUser reimburseListOfUser) {
        List<Settlement> settlementByRouteId = settlementRepository.findSettlementByRouteId(id);
        Collections.sort(settlementByRouteId);
        //   reimburseListOfUser.setStartPlace(settlementByRouteId.get().s);
    }

//    @Override
//    public ResponseResult GetReimburseStatistic_person(Long id) {
//        Map<String, List<Route>> temp = new HashMap<>();
//        List<Route> reimburseofperson = routeRepository.findRouteByUserId(id);
//        for (Route route : reimburseofperson) {
//            String time = route.getApplyTime().substring(0, 10);
//            if(temp.keySet().contains(time)){
//                temp.get(time).add(route);
//            }
//            else{
//                List<Route> list = new LinkedList<>();
//                list.add(route);
//                temp.put(time,list);
//            }
//        }
//
//        Set<String> keys = temp.keySet();
//
//        List<ReimburseStatisticByDay> list = new LinkedList<>();
//        double sum=0;int time=0;
//        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
//            String keyStr = (String) iter.next();
//            ReimburseStatisticByDay day = new ReimburseStatisticByDay();
//            day.setDate(keyStr);
//            ReimburseDetail is0 = new ReimburseDetail();
//            ReimburseDetail is1 = new ReimburseDetail();
//            ReimburseDetail isfu1 = new ReimburseDetail();
//            for (Route route : temp.get(keyStr)) {
//                if(route.getIsReimburse()==-1){
//                    isfu1.setMoney(isfu1.getMoney()+route.getPrice());
//                    isfu1.setTime(isfu1.getTime()+1);
//                }
//                if(route.getIsReimburse()==0){
//                    is0.setMoney(is0.getMoney()+route.getPrice());
//                    is0.setTime(is0.getTime()+1);
//                }
//                if(route.getIsReimburse()==1){
//                    is1.setMoney(is1.getMoney()+route.getPrice());
//                    is1.setTime(is1.getTime()+1);
//                }
//            }
//            sum+=is1.getMoney();
//            time+=is1.getTime();
//            day.setRejected(isfu1);
//            day.setImbursed(is1);
//            day.setIsimbursing(is0);
//            list.add(day);
//        }
//        ReimburseStatistic ans = new ReimburseStatistic();
//        ans.setDataOfEveryDay(list);
//        ReimburseDetail summ = new ReimburseDetail();
//        summ.setTime(time);
//        summ.setMoney(sum);
//        ans.setSum(summ);
//
//        return new ResponseResult(ans);
//    }
//
//    private void searchStartAndEndPlaceByRouteId(Long id, ReimburseListOfUser reimburseListOfUser) {
//        List<Settlement> settlementByRouteId = settlementRepository.findSettlementByRouteId(id);
//        Collections.sort(settlementByRouteId);
//        //   reimburseListOfUser.setStartPlace(settlementByRouteId.get().s);
//    }

    //包装
    private ReimburseModel packReimburseModel(Reimburse r) {
        if (r == null) return null;
        return new ReimburseModel(r, routeService.findFDRouteById(r.getRouteId()));
    }

    private List<ReimburseModel> packReimburseModels(List<Reimburse> reimburses) {
        if (reimburses == null) return null;
        List<ReimburseModel> reimburseModels = new ArrayList<>();
        for (Reimburse r : reimburses) {
            reimburseModels.add(this.packReimburseModel(r));
        }
        return reimburseModels;
    }
}
