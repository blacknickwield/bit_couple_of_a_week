package com.gjf.bit_couple_of_a_week.controller;

import com.gjf.bit_couple_of_a_week.domain.Couple;
import com.gjf.bit_couple_of_a_week.domain.User;
import com.gjf.bit_couple_of_a_week.exception.CoupleDaoException;
import com.gjf.bit_couple_of_a_week.service.CoupleService;
import com.gjf.bit_couple_of_a_week.service.UserService;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import com.gjf.bit_couple_of_a_week.vo.CoupleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.gjf.bit_couple_of_a_week.myenum.CoupleStatus.NEW;


@RequestMapping("/couple")
@RestController
@CrossOrigin
public class CoupleController {
    @Autowired
    private CoupleService coupleService;
    @Autowired
    private UserService userService;

    /***
     * 发起组成cp的请求（需指明对方的id）
     * @param coupleVo
     * @return responseResult
     */
    @PostMapping("/launch")
    public ResponseResult launch(@RequestBody(required = true) CoupleVo coupleVo) {
        System.out.println(coupleVo);
        Couple couple = CoupleVo.convertToPo(coupleVo);
        Integer id = null;
        // 对方用户的id
        if (couple.getLauncher().equals(couple.getMaleId())) {
            id = couple.getFemaleId();
        } else {
            id = couple.getMaleId();
        }
        System.out.println(id);
        User user = userService.getUserById(id);
        if (user == null)
            throw new CoupleDaoException("未找到目标用户，请重新检查cp请求！");
        couple.setStatus(NEW.getStatus());
        coupleService.addNewCouple(couple);
        return new ResponseResult()
                .code(200)
                .message("发送cp申请成功");
    }

    /***
     * 男用户请求系统为自己随机分配cp
     * @param maleId
     * @return
     */
    @PostMapping("/launchRandom/male")
    public ResponseResult launchRandomByMale(@RequestParam(name = "maleId") Integer maleId) {
        Couple couple = coupleService.randomAddNewCoupleForMale(maleId);
        if (couple == null) {
            return new ResponseResult()
                    .code(200)
                    .message("当前没有发起CP请求的异性用户，已为您发起新请求");
        } else {
            return new ResponseResult()
                    .code(200)
                    .data("couple", couple)
                    .message("请求成功");
        }
    }

    /***
     * 女用户请求系统为自己随机分配cp
     * @param femaleId
     * @return
     */
    @PostMapping("/launchRandom/female")
    public ResponseResult launchRandomByFemale(@RequestParam(name = "femaleId") Integer femaleId) {
        Couple couple = coupleService.randomAddNewCoupleForFemale(femaleId);
        if (couple == null) {
            return new ResponseResult()
                    .code(200)
                    .message("当前没有发起CP请求的异性用户，已为您发起请求");
        } else {
            return new ResponseResult()
                    .code(200)
                    .data("couple", couple)
                    .message("请求成功");
        }
    }

    /***
     * 查看自己发起的新的cp请求
     * @param id
     * @return
     */
    @GetMapping("/my")
    public ResponseResult getNewCoupleAppliedByMyself(@RequestParam(name = "id") Integer id) {
        return new ResponseResult()
                .code(200)
                .data("couple", CoupleVo.convertToVo(coupleService.getNewCoupleByLauncher(id)))
                .message("请求成功");
    }

    /***
     * 查看自己发起的所有cp请求
     * @param id
     * @return
     */
    @GetMapping("/myAll")
    public ResponseResult getAllCoupleAppliedByMyself(@RequestParam(name = "id") Integer id) {
        return new ResponseResult()
                .code(200)
                .data("couples", coupleService.getAllCoupleAppliedBySelf(id)
                        .stream()
                        .map(CoupleVo::convertToVo)
                        .collect(Collectors.toList()))
                .message("请求成功");
    }

    /***
     * 查看其他人向自己发出的组成cp的请求
     * @param id
     * @return
     */
    @GetMapping("/other")
    public ResponseResult getCoupleAppliedByOther(@RequestParam(name = "id") Integer id) {
        return new ResponseResult()
                .code(200)
                .data("couples", coupleService.getNewCoupleAppliedByOther(id)
                        .stream()
                        .map(CoupleVo::convertToVo)
                        .collect(Collectors.toList()))
                .message("请求成功");
    }



    /***
     * 接受cp请求
     * 接受请求后会自动拒绝其他的cp请求
     * @param coupleId
     * @return
     */
    @PostMapping("/accept")
    public ResponseResult accept(@RequestParam(name = "coupleId") Integer coupleId, @RequestParam(name = "id") Integer id) {
        Couple couple = coupleService.acceptCouple(coupleId, id);
        return new ResponseResult()
                .code(200)
                .data("coupleInfo", CoupleVo.convertToVo(couple))
                .message("接受请求成功");
    }


    /***
     * 拒绝cp请求
     * @param coupleId
     * @return
     */
    @PostMapping("/reject")
    public ResponseResult reject(@RequestParam(name = "coupleId") Integer coupleId) {
        coupleService.rejectCouple(coupleId);
        return new ResponseResult()
                .code(200)
                .message("拒绝cp成功");
    }
}
