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
package io.trino.plugin.pinot.client;

import com.google.common.collect.ImmutableList;
import com.google.inject.ConfigurationException;
import com.google.inject.spi.Message;
import io.airlift.configuration.Config;
import io.trino.plugin.base.ssl.SslTrustConfig;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;

public class PinotGrpcServerQueryClientTlsConfig
        extends SslTrustConfig
{
    private String sslProvider = "JDK";

    @NotNull
    public String getSslProvider()
    {
        return sslProvider;
    }

    @Config("ssl-provider")
    public PinotGrpcServerQueryClientTlsConfig setSslProvider(String sslProvider)
    {
        this.sslProvider = sslProvider;
        return this;
    }

    @PostConstruct
    public void validate()
    {
        if (getKeystorePath().isPresent() && getKeystorePassword().isEmpty()) {
            throw new ConfigurationException(ImmutableList.of(new Message("pinot.grpc.tls.keystore-password must set when pinot.grpc.tls.keystore-path is given")));
        }
        if (getTruststorePath().isPresent() && getTruststorePassword().isEmpty()) {
            throw new ConfigurationException(ImmutableList.of(new Message("pinot.grpc.tls.truststore-password must set when pinot.grpc.tls.truststore-path is given")));
        }
    }
}
