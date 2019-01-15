package com.bwie.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bwie.bean.login.Login;

import com.bwie.greendao.LoginDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig loginDaoConfig;

    private final LoginDao loginDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        loginDaoConfig = daoConfigMap.get(LoginDao.class).clone();
        loginDaoConfig.initIdentityScope(type);

        loginDao = new LoginDao(loginDaoConfig, this);

        registerDao(Login.class, loginDao);
    }
    
    public void clear() {
        loginDaoConfig.clearIdentityScope();
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

}
