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

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;
import static io.trino.plugin.base.ssl.TruststoreType.JKS;
import static io.trino.plugin.base.ssl.TruststoreType.PKCS12;

public class TestSslTrustConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(SslTrustConfig.class)
                .setKeystorePath(null)
                .setKeystorePassword(null)
                .setTruststorePath(null)
                .setTruststorePassword(null)
                .setTruststoreType(JKS));
    }

    @Test
    public void testExplicitPropertyMappings()
            throws IOException
    {
        Path truststorePath = Files.createTempFile("truststore", ".p12");
        Path keystoreFile = Files.createTempFile("keystore", ".jks");
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("keystore-path", keystoreFile.toString())
                .put("keystore-password", "keystore-password")
                .put("truststore-path", truststorePath.toString())
                .put("truststore-password", "truststore-password")
                .put("truststore-type", "PKCS12")
                .buildOrThrow();

        SslTrustConfig expected = new SslTrustConfig()
                .setKeystorePath(keystoreFile.toFile())
                .setKeystorePassword("keystore-password")
                .setTruststorePath(truststorePath.toFile())
                .setTruststorePassword("truststore-password")
                .setTruststoreType(PKCS12);
        assertFullMapping(properties, expected);
    }
}
