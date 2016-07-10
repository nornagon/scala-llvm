import org.bridj.{BridJ, Pointer}


package object llvm extends llvm.binding.LLVMLibrary {
  BridJ.register()

  @native def LLVMInitializeX86TargetInfo(): Unit
  @native def LLVMInitializeX86Target(): Unit
  @native def LLVMInitializeX86TargetMC(): Unit
  def LLVMInitializeNativeTarget(): Unit = {
    LLVMInitializeX86TargetInfo()
    LLVMInitializeX86Target()
    LLVMInitializeX86TargetMC()
  }
  @native def LLVMInitializeX86AsmParser(): Unit
  @native def LLVMInitializeX86AsmPrinter(): Unit
  @native def LLVMInitializeX86Disassembler(): Unit
  def LLVMInitializeNativeAsmParser(): Unit = LLVMInitializeX86AsmParser()
  def LLVMInitializeNativeAsmPrinter(): Unit = LLVMInitializeX86AsmPrinter()
  def LLVMInitializeNativeDisassembler(): Unit = LLVMInitializeX86Disassembler()

  implicit def stringToPB(s: String): Pointer[java.lang.Byte] = Pointer.pointerToCString(s)
  implicit def arrayToPointer[T](xs: Array[T]): Pointer[T] = Pointer.pointerToArray(xs)
}
