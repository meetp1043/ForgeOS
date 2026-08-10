# Future Vector Storage

The ForgeOS **Memory Engine** and **Context Engine** will require vector similarity search (e.g., RAG pipelines).

## Extension Strategy
Because we have established a strong PostgreSQL foundation, we can seamlessly enable the **`pgvector`** extension in the future.

This allows us to store embeddings (e.g., `vector(1536)`) directly alongside our relational context (tenant ID, project ID). This prevents the complex architecture required to sync relational permissions from Postgres into a separate dedicated vector database (like Pinecone or Milvus).
