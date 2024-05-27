# Campo-Minado
Um jogo de campo minado, que é executado e funciona no terminal, implementado em Java.

# Como Jogar?
Para iniciar um novo jogo, basta instanciar um novo objeto da classe campo.
O construtor deve receber dois parâmetros, o primerio é a largura/altura do quadrado, e o segundo é o nível de dificuldade, que vai de 1 a 3.

Após isso, deve-se chamar o método init() para começar

A jogada deve ser passada da seguinte forma:

coordenada vertical
    |      
    |    coordenada horizontal
    v      --->                             
    X   #   Y   #   J   (j para jogar, f para colocar uma bandeira)

#Exemplo

Campo c = new Campo(10, 2); <--- Instancia campo de tamanho 10x10 de dificuldade 2
c.init();

0 1 2 3 4 5 6 7 8 9
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 0
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 1
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 2
■ ■ ■ ■ x ■ ■ ■ ■ ■ 3   Para jogar na cordenada indicada, escreva 3#4#j
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 4
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 5   Para marcar uma bandeira na cordenada indicada, escreva 3#4#f
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 6
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 7
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 8
■ ■ ■ ■ ■ ■ ■ ■ ■ ■ 9
