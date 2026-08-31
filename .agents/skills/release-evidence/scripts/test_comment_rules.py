# SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
# SPDX-License-Identifier: Apache-2.0

from __future__ import annotations

import tempfile
import unittest
from pathlib import Path

from comment_rules import find_comment_rule_errors


class CommentRuleTests(unittest.TestCase):
    def audit(self, name: str, source: str) -> list[str]:
        with tempfile.TemporaryDirectory() as directory:
            root = Path(directory)
            path = root / name
            path.write_text(source, encoding="utf-8")
            return find_comment_rule_errors(root, [root])

    def test_flags_long_python_comments_and_docstrings(self) -> None:
        errors = self.audit(
            "sample.py",
            '"""One. Two. Three. Four. Five. Six."""\n'
            "# One. Two. Three. Four.\n"
            "VALUE = 1\n",
        )

        self.assertEqual(len(errors), 2)
        self.assertIn("maximum is 5", errors[0])
        self.assertIn("maximum is 3", errors[1])

    def test_flags_java_comment_but_allows_five_javadoc_sentences(self) -> None:
        errors = self.audit(
            "Sample.java",
            "/** One. Two. Three. Four. Five. */\n"
            "final class Sample {\n"
            "  // One. Two. Three. Four.\n"
            "}\n",
        )

        self.assertEqual(len(errors), 1)
        self.assertIn("Sample.java:3", errors[0])

    def test_ignores_directives_and_string_content(self) -> None:
        errors = self.audit(
            "sample.ts",
            "/* SPDX-License-Identifier: Apache-2.0 */\n"
            "// eslint-disable-next-line no-console\n"
            "const url = 'http://localhost:8080/api';\n"
            "// @ts-expect-error\n",
        )

        self.assertEqual(errors, [])

    def test_groups_adjacent_line_comments(self) -> None:
        errors = self.audit(
            "sample.java",
            "// One.\n"
            "// Two.\n"
            "// Three.\n"
            "// Four.\n",
        )

        self.assertEqual(len(errors), 1)
        self.assertIn("sample.java:1", errors[0])

    def test_flags_long_sql_comment_blocks(self) -> None:
        errors = self.audit(
            "sample.sql",
            "-- SPDX-License-Identifier: Apache-2.0\n"
            "/* One. Two. Three. Four. */\n"
            "SELECT 1;\n",
        )

        self.assertEqual(len(errors), 1)
        self.assertIn("sample.sql:2", errors[0])

    def test_accepts_three_comment_sentences(self) -> None:
        errors = self.audit(
            "sample.scss",
            "/* One. Two. Three. */\n"
            ".item { color: inherit; }\n",
        )

        self.assertEqual(errors, [])


if __name__ == "__main__":
    unittest.main()
