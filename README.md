# Desafio técnico para desenvolvedor

Dados de entrada devem estar em '/home/data/in'

Dados de saida irão para '/home/data/out'

No arquivo de saída o sistema deverá possuir os seguintes dados:

	• Quantidade de clientes no arquivo de entrada
	
	• Quantidade de vendedores no arquivo de entrada
	
	• ID da venda mais cara
	
	• O pior vendedor


Dados base

	001ç1234567891234çPedroç50000
	001ç3245678865434çPauloç40000.99
	002ç2345675434544345çJose da SilvaçRural
	002ç2345675433444345çEduardo PereiraçRural
	003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
	003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
	
Mudanças possíveis:

	* Se ao utilizar um arquivo CSV é necessário somente alterar o valor da constante 'SPLITLINE' para ',' ou ';'
	* Se quiser alterar tanto o PATH de entrada quando o de saída. Para entrada deve-se mudar em Application (PATH) e FileDecoder (FILEPATH), para saida em FileDAO (OUTPUTPATH)