import os

os.system("./codegen.bash < program.txt > output.asm && nasm -f elf64 output.asm -o output.o && gcc output.o -o output && ./output && rm output.o && rm output")
