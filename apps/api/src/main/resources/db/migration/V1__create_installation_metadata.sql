-- SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
-- SPDX-License-Identifier: Apache-2.0

CREATE TABLE installation_metadata (
    metadata_key VARCHAR(32) PRIMARY KEY,
    installation_id UUID NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT installation_metadata_singleton CHECK (metadata_key = 'installation')
);
