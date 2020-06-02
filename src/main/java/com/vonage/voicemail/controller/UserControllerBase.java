package com.vonage.voicemail.controller;

import com.vonage.voicemail.common.Constant;
import com.vonage.voicemail.service.UserTableService;

abstract class UserControllerBase {
    private final static String User_TABLE_NAME = Constant.user_Table_Name;
    private final static UserTableService userTableService = new UserTableService( User_TABLE_NAME );
    protected static final String User_LIST = "userList";
    
    protected UserTableService getUserTableService() {
        return userTableService;
    }
    
    
}
