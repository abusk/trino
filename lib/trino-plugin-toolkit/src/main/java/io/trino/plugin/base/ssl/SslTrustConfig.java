/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.base.ssl;

import io.airlift.configuration.Config;
import io.airlift.configuration.ConfigDescription;
import io.airlift.configuration.ConfigSecuritySensitive;
import io.airlift.configuration.LegacyConfig;
import io.airlift.configuration.validation.FileExists;

import java.io.File;
import java.util.Optional;

import static io.trino.plugin.base.ssl.TruststoreType.JKS;

public class SslTrustConfig
{
    private File keystorePath;
    private String keystorePassword;
    private File truststorePath;
    private String truststorePassword;
    private TruststoreType truststoreType = JKS;
    private TruststoreType keystoreType = JKS;

    public Optional<@FileExists File> getKeystorePath()
    {
        return Optional.ofNullable(keystorePath);
    }

    @Config("keystore-path")
    @LegacyConfig({"keystore.location", "keystore.path"})
    public SslTrustConfig setKeystorePath(File keystorePath)
    {
        this.keystorePath = keystorePath;
        return this;
    }

    public Optional<String> getKeystorePassword()
    {
        return Optional.ofNullable(keystorePassword);
    }

    @Config("keystore-password")
    @LegacyConfig({"keystore.password", "keystore.key"})
    @ConfigSecuritySensitive
    public SslTrustConfig setKeystorePassword(String keystorePassword)
    {
        this.keystorePassword = keystorePassword;
        return this;
    }

    public Optional<TruststoreType> getKeystoreType()
    {
        return Optional.ofNullable(keystoreType);
    }

    @Config("keystore-type")
    @LegacyConfig("keystore.type")
    public SslTrustConfig setKeystoreType(TruststoreType keystoreType)
    {
        this.keystoreType = keystoreType;
        return this;
    }

    public Optional<@FileExists File> getTruststorePath()
    {
        return Optional.ofNullable(truststorePath);
    }

    @Config("truststore-path")
    @LegacyConfig({"truststore.location", "truststore.path"})
    public SslTrustConfig setTruststorePath(File truststorePath)
    {
        this.truststorePath = truststorePath;
        return this;
    }

    public Optional<String> getTruststorePassword()
    {
        return Optional.ofNullable(truststorePassword);
    }

    @Config("truststore-password")
    @LegacyConfig({"truststore.password", "truststore.key"})
    @ConfigSecuritySensitive
    public SslTrustConfig setTruststorePassword(String truststorePassword)
    {
        this.truststorePassword = truststorePassword;
        return this;
    }

    public Optional<TruststoreType> getTruststoreType()
    {
        return Optional.ofNullable(truststoreType);
    }

    @Config("truststore-type")
    @LegacyConfig("truststore.type")
    @ConfigDescription("The file format of the trust store file")
    public SslTrustConfig setTruststoreType(TruststoreType truststoreType)
    {
        this.truststoreType = truststoreType;
        return this;
    }
}
