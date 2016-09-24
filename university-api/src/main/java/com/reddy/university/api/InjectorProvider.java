package com.reddy.university.api;

import com.google.inject.Injector;

/**
 * Created by deven on 9/24/2016.
 */
public class InjectorProvider {
    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        InjectorProvider.injector = injector;
    }

    private static Injector injector;

}
