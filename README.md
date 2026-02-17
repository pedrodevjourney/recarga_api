# Recarga API

API para o MVP de carros elétricos (recarga).

## OpenEV Data – Catálogo de veículos

O catálogo de modelos de veículos elétricos é carregado a partir do dataset [OpenEV Data](https://github.com/open-ev-data/open-ev-data-dataset).

### Carga inicial (uma vez, quando o banco está vazio)

1. Coloque o arquivo JSON do OpenEV em **`src/main/resources/open-ev-data.json`**.
2. Ao subir a aplicação, se a tabela `ev_catalog` estiver vazia, o loader lê esse arquivo e persiste os dados no PostgreSQL.
3. Se o arquivo não existir, a aplicação sobe normalmente e apenas registra um aviso em log (não falha).

### Como obter o JSON

Baixe a versão mais recente do repositório OpenEV:

```bash
LATEST_TAG=$(curl -s https://api.github.com/repos/open-ev-data/open-ev-data-dataset/releases/latest | jq -r .tag_name)
curl -L -o src/main/resources/open-ev-data.json "https://github.com/open-ev-data/open-ev-data-dataset/releases/download/$LATEST_TAG/open-ev-data-$LATEST_TAG.json"
```

Ou use uma versão específica (ex.: `v1.24.0`):

```bash
curl -L -o src/main/resources/open-ev-data.json "https://github.com/open-ev-data/open-ev-data-dataset/releases/download/v1.24.0/open-ev-data-v1.24.0.json"
```

Documentação completa: [How to Consume OpenEV Data](https://github.com/open-ev-data/open-ev-data-dataset/blob/main/docs/HOW_TO_CONSUME.md).
