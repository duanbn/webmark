package com.dy.webmark.service;

import com.dy.webmark.entity.Favorite;
import com.dy.webmark.exception.FavoriteException;

public interface IFavoriteService {

    public void addFavorite(Favorite favo) throws FavoriteException;

    public Favorite getFavoriteById(int favoId) throws FavoriteException;

}
