package com.exotikosteam.exotikos.models;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ada on 11/11/16.
 */

@Database(name = ExotikosDatabase.NAME, version = ExotikosDatabase.VERSION)
public class ExotikosDatabase {
    public static final String NAME = "exotikosDB";
    public static final int VERSION = 1;
}
