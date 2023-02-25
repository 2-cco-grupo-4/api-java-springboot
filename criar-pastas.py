import os 

nomes = ["Danilo","Davi","Eduarda","Kelvin","Rafael","Ryan"] 
os.system("mkdir entregavel-spr1") 
for i in nomes : 
    os.system(f"mkdir entregavel-spr1/{i} && touch '.' > entregavel-spr1/{i}/entregavel-diego.txt")
