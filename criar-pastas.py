import os 

nomes = ["Danilo","Davi","Eduarda","Kelvin","Rafael","Ryan"] 

for i in nomes : 
    os.system(f"mkdir {i} && touch '.' > {i}/entregavel-diego.txt")
