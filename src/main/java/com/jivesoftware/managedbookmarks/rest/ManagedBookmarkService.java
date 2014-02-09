package com.jivesoftware.managedbookmarks.rest;

import com.google.common.collect.Maps;
import com.jivesoftware.base.User;
import com.jivesoftware.community.EntityDescriptor;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.UserAuthoredObject;
import com.jivesoftware.community.browse.rest.ItemBeanBuilder;
import com.jivesoftware.community.browse.rest.ItemBeanPropertyProvider;
import com.jivesoftware.community.browse.rest.impl.AbstractItemBeanBuilder;
import com.jivesoftware.community.browse.rest.impl.JiveObjectAuthorProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectLastActivityDateProviderImpl;
import com.jivesoftware.community.favorites.Favorite;
import com.jivesoftware.community.favorites.ObjectFavorite;
import com.jivesoftware.community.favorites.rest.BookmarkBean;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemTagInfoProviderImpl;
import com.jivesoftware.community.user.rest.UserItemBean;
import com.jivesoftware.community.util.BasePermHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/*
 * $Revision$
 * $Date$
 *
 * Copyright (C) 1999-2011 Jive Software. All rights reserved.
 *
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */
import org.codehaus.jra.HttpResource;

import javax.ws.rs.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Produces("application/json")
public interface ManagedBookmarkService  {

    @POST
    @Path("/create/{bookmarkName}")
    public void createBookmark(@PathParam("bookmarkName") String bookmarkName);


}
