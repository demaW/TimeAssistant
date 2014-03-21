package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.model.Team;
import com.java.task11.controller.dao.factory.DAOFactory;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class TeamService implements IBaseService<Team> {

    @Override
    public Team getByID(Integer id) {
        return DAOFactory.getInstance().getTeamDAO().getByID(id);
    }

    @Override
    public void save(Team element) {
        DAOFactory.getInstance().getTeamDAO().save(element);
    }

    @Override
    public void update(Team element) {
        DaoFactory.getInstance().getTeamDao().update(element);
    }

    @Override
    public void delete(Team element) {
        DaoFactory.getInstance().getTeamDao().delete(element);
    }

    @Override
    public List<Team> getListOfObjects() {
        return DaoFactory.getInstance().getTeamDao().getListOfObjects();
    }
}
