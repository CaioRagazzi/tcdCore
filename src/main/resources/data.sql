INSERT INTO core.genero (descricao) SELECT 'Ação' WHERE NOT EXISTS (SELECT 1 FROM core.genero WHERE descricao = 'Ação');
INSERT INTO core.genero (descricao) SELECT 'Terror' WHERE NOT EXISTS (SELECT 1 FROM core.genero WHERE descricao = 'Terror');
INSERT INTO core.genero (descricao) SELECT 'Aventura' WHERE NOT EXISTS (SELECT 1 FROM core.genero WHERE descricao = 'Aventura');
INSERT INTO core.genero (descricao) SELECT 'Romance' WHERE NOT EXISTS (SELECT 1 FROM core.genero WHERE descricao = 'Aventura');
INSERT INTO core.classificacao (descricao) SELECT 'Livre' WHERE NOT EXISTS (SELECT 1 FROM core.classificacao WHERE descricao = 'Livre');
INSERT INTO core.classificacao (descricao) SELECT 'Maior de 18 anos' WHERE NOT EXISTS (SELECT 1 FROM core.classificacao WHERE descricao = 'Maior de 18 anos');
INSERT INTO core.classificacao (descricao) SELECT 'Maior de 16 anos' WHERE NOT EXISTS (SELECT 1 FROM core.classificacao WHERE descricao = 'Maior de 16 anos');
