package com.mage.crm.service;

import com.mage.crm.dao.UserDao;
import com.mage.crm.model.UserModel;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.Base64Util;
import com.mage.crm.util.Md5Util;
import com.mage.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource//相当于创建一个对象
    private UserDao userDao;

    public List<User> queryAllCustomerManager() {
        List<User> userList = userDao.queryAllCustomerManager();
        return userList;
    }

    /**
     * 用户合法性的检测
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel userLogin(String userName, String userPwd) {
        User user = userDao.queryUserByName(userName);
        AssertUtil.isTrue(null == user, "用户不存在");
        AssertUtil.isTrue("0".equals(user.getIsValid()), "用户已经注销");
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(user.getUserPwd()), "密码错误");
        return createUserModel(user);
    }

    /**
     * 创建UserModel* @param user
     *
     * @return
     */
    public UserModel createUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        String id = Base64Util.encode(user.getId());//加密之后的userId;
        userModel.setUserId(id);
        return userModel;
    }

    /**
     * 用户修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void updatePwd(String userId, String oldPassword, String newPassword, String
            confirmPassword) {
        AssertUtil.isTrue(null == userId, "非法用户");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "两次密码输入不一致");
        User user = userDao.queryUserById(userId);
        AssertUtil.isTrue(null == user, "用户被冻结，不允许修改密码");
        oldPassword = Md5Util.encode(oldPassword);
        AssertUtil.isTrue(!oldPassword.equals(user.getUserPwd()), "原始密码输入错误");
        newPassword = Md5Util.encode(newPassword);
        AssertUtil.isTrue(userDao.updatePwd(userId, newPassword) < 1, "操作失败");
    }
}