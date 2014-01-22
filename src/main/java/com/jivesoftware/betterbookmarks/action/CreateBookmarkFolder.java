package com.jivesoftware.betterbookmarks.action;

import com.jivesoftware.community.action.JiveActionSupport;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/15/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateBookmarkFolder extends JiveActionSupport {

    public String doAdd(){
        System.out.println("CreateBookmarkFolder called create");
        return "success";
    }


    public String execute() {
        System.out.println("CreateBookmarkFolder called execute");
        return "success";
    }
}
