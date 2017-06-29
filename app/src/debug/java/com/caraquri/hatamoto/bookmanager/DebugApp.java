package com.caraquri.hatamoto.bookmanager;

import com.facebook.stetho.Stetho;

public class DebugApp extends App {

    @Override
    protected void initializeStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
