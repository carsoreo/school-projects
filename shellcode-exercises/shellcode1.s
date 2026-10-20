.text
.global main
main:
    jmp saveme
shellcode:
    pop %rsi
    mov $1, %rax # opcode for write system call
    mov $1, %rdi # 1st arg, fd = 1
    mov %rsi, %rsi
    mov $9, %rdx # 3rd arg, len
    syscall # system call interrupt
    mov $60, %rax # opcode for exit system call
    mov $0, %rdi # 1st arg, exit(0)
    syscall # system call interrupt
saveme:
    call shellcode
    .string "clorejol\n"