/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.kernel.impl.operations;

import org.fcrepo.kernel.api.Transaction;
import org.fcrepo.kernel.api.identifiers.FedoraId;

import java.io.InputStream;
import java.net.URI;

/**
 * Builder for operations to update non-rdf sources
 *
 * @author bbpennel
 */
public class UpdateNonRdfSourceOperationBuilder extends AbstractNonRdfSourceOperationBuilder {
    protected UpdateNonRdfSourceOperationBuilder(final Transaction transaction, final FedoraId rescId,
                                                 final InputStream stream) {
        super(transaction, rescId, stream);
    }

    protected UpdateNonRdfSourceOperationBuilder(final Transaction transaction, final FedoraId rescId,
                                                 final String handling,
                                                 final URI contentUri) {
        super(transaction, rescId, handling, contentUri);
    }

    @Override
    public UpdateNonRdfSourceOperation build() {
        final UpdateNonRdfSourceOperation operation;
        if (externalURI != null && externalType != null) {
            operation = new UpdateNonRdfSourceOperation(transaction, resourceId, externalURI, externalType);
        } else {
            operation = new UpdateNonRdfSourceOperation(transaction, resourceId, content);
        }

        operation.setUserPrincipal(userPrincipal);
        operation.setDigests(digests);
        operation.setFilename(filename);
        operation.setContentSize(contentSize);
        operation.setMimeType(mimeType);

        return operation;
    }
}
