# Context Compression

When a candidate exceeds the Token Budget, it must be compressed. Compression reduces size while attempting to retain semantic value.

## Compression Techniques

1. **Truncation**: The bluntest instrument. Used for low-priority data like tool logs (e.g., keeping the first 50 and last 50 lines of a 1000-line stack trace).
2. **Deduplication**: Removing identical snippets.
3. **Summarization**: Using a cheaper, faster LLM to reduce a document to its core facts.
4. **Hierarchical Summarization**: Summarizing at the chapter level, then summarizing the chapters.
5. **Code Extraction (AST Parsing)**: 
   - Removing comments and whitespace.
   - Preserving function signatures while stripping the implementation bodies of non-target dependencies.
6. **Document Extraction**: Pulling out only the headers and bolded constraints from a PRD.
7. **History Compression**: Squashing 20 Git commit messages into a single 3-sentence summary of recent work.

## The Preservation Rule
**Critical information must be preserved.**
If a security policy or acceptance criteria cannot be compressed without losing its explicit meaning, it must be retained in full, and lower-priority items must be dropped instead.
