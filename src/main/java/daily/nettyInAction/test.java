//package daily.nettyInAction;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.CompositeByteBuf;
//import io.netty.buffer.Unpooled;
//
//import java.nio.ByteBuffer;
//
///**
// * Created by wb-zj373670 on 2018/5/13.
// */
//public class test {
//
//
//    public static void main(String[] args) {
//        CompositeByteBuf  compBuf = Unpooled.compositeBuffer();
//        ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());
//        ByteBuf writeBur = Unpooled.buffer(5);
//        ByteBuffer buffer = byteBuf.nioBuffer();
//        byte[] bytes = new byte[buffer.remaining()];
//        buffer.get(bytes);
//        ByteBuf byteBuf1 = Unpooled.directBuffer();
//        writeBur.writeBytes(byteBuf,2);
//        System.out.println(byteBuf.readerIndex() + "-------" + writeBur.writerIndex());
////        for(int i = 0; i < byteBuf.capacity(); i++){
////            System.out.println(new String(bytes));
////
////        }
//
//    }
//}
