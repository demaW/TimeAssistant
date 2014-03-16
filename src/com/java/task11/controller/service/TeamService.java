package com.java.task11.controller.service;

import com.java.task11.controller.dao2.DaoFactory;
import com.java.task11.model.Team;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class TeamService implements IBaseService<Team> {

    @Override
    public Team getByID(Integer id) {
        return DaoFactory.getInstance().getTeamDao().getByID(id);
    }

    @Override
    public void save(Team element) {
        DaoFactory.getInstance().getTeamDao().save(element);
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
