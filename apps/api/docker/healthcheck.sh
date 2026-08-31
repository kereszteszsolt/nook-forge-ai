#!/usr/bin/env bash
# SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
# SPDX-License-Identifier: Apache-2.0

set -eu

exec 3<>/dev/tcp/127.0.0.1/8080
printf 'GET /actuator/health/readiness HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n' >&3
IFS= read -r status_line <&3

case "$status_line" in
  *" 200 "*) exit 0 ;;
  *) exit 1 ;;
esac
