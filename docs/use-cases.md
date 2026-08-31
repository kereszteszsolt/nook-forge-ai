# Concrete use cases

These examples keep the product useful at home and avoid a vague chat-only scope.

## Turn a home goal into a plan

A user enters “paint the apartment in October” and adds room notes. Nook Forge returns phases, tasks, dependencies, missing facts, and risks. The result can be saved as Markdown.

## Process loose notes

A user uploads a garage note with shopping, repair, and cleanup ideas. Nook Forge groups the items, extracts clear tasks, and marks questions that still need an answer.

## Compare household offers

A user adds two internet, insurance, or repair offers. Nook Forge extracts common terms, differences, missing details, and warning points. It does not make a financial or legal choice for the user.

## Prepare a trip from saved files

A user adds booking notes, a hotel confirmation, and a checklist. Nook Forge builds one trip summary, lists dates and gaps, and creates a packing or action list. No live travel search is required.

## Review a CV or letter

A user adds a CV, cover letter, or formal message. Nook Forge finds unclear text, repeated points, missing details, and concrete edits. The original file is not changed.

## Find changes between versions

A user uploads two versions of a policy, contract draft, README, or project plan. Nook Forge marks added, removed, and changed facts, then calls out items that may need review.

## Organize a personal file set

A user adds several notes and documents or one ZIP. Nook Forge builds a manifest, groups accepted files, skips binary or generated data, and creates a workspace summary.

## Review a software project archive

A developer uploads a project ZIP. Nook Forge inspects build files, source layout, configuration, Docker files, and current documentation. It can report conflicts such as Java 21 in `pom.xml` but Java 17 in a Dockerfile.

## Generate proposed project documentation

From observed repository facts, Nook Forge can draft a README, overview, architecture guide, configuration guide, development guide, and user guide. Each statement is marked as observed, inferred, or unresolved. Generated files are exported separately or in a copied archive; source files stay unchanged.

## Safety boundary

Nook Forge does not execute uploaded code, delete user files, run archive scripts, enable macros, or silently send content to a cloud service. High-stakes documents still need human review.
