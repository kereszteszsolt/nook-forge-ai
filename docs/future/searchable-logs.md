# Candidate future option: Elasticsearch and Kibana

## Status

Candidate only. Release 0.5 uses safe JSON stdout logs, Prometheus, Grafana, and Langfuse.

## Entry conditions

Consider Elasticsearch and Kibana only when operators have a measured need to search or retain logs across many runs and local container output is not enough. The new stack must solve a different problem from Langfuse and Prometheus.

## Guardrails

- optional deployment only;
- loopback bind by default;
- bounded local retention;
- no raw documents, prompts, responses, secrets, or file paths;
- index templates with safe field types;
- no task or file content in dashboards;
- clear disk-use and cleanup rules;
- verified startup without the search stack.

A future release plan must include the full storage and privacy cost. Kibana is not added by itself without Elasticsearch.
