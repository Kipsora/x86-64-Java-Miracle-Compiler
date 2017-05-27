import sys, os

filename = "output.asm"
prefix = filename.split('.')[0]
os.system('nasm -f elf64 -g -F dwarf ' + filename +  ' && gcc -g ' + prefix + '.o -o ' + prefix + ' && ./' + prefix + ' ' + ' '.join(sys.argv[2:])) 
