package com.pandamy.maeruoc.di;

import androidx.annotation.VisibleForTesting;

import com.pandamy.maeruoc.service.ApiService;
import com.pandamy.maeruoc.service.DummyApiService;

public abstract class DI {
    // FIELDS --------------------------------------------------------------------------------------

    private static ApiService apiService = new DummyApiService();

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns the only instance of {@link ApiService}
     * @return the only instance of {@link ApiService}
     */
    public static ApiService getApiService() {
        return apiService;
    }

    /**
     * Returns always a new instance on {@link ApiService}.
     * Useful for tests, so we ensure the context is clean.
     * @return a {@link ApiService}
     */
    @VisibleForTesting
    public static ApiService getNewInstanceApiService() {
        return new DummyApiService();
    }
}
