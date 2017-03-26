package com.miracle.scanner.listener;

import com.miracle.scanner.environment.manager.MiracleEnvironmentReader;

public class MiracleASTreeBuilder extends MiracleRuntimeMaintainer {
    public MiracleASTreeBuilder() {
        super(new MiracleEnvironmentReader());
    }

}
