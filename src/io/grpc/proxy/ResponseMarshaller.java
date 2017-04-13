package io.grpc.proxy;

import io.grpc.MethodDescriptor.Marshaller;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseMarshaller implements Marshaller<Object> {

	@Override
	public InputStream stream(Object value) {
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
		ResponseMessageTransfer responseMessageTransfer = new ResponseMessageTransfer();
		responseMessageTransfer.setObject(value);

		Schema objSchema = RuntimeSchema.getSchema(responseMessageTransfer.getClass());
		LinkedBuffer writeBuffer1 = LinkedBuffer.allocate(1000);
		try {
			ProtobufIOUtil.writeTo(outputstream, responseMessageTransfer, objSchema, writeBuffer1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		byte[] arr = outputstream.toByteArray();
		InputStream messageIs = new ByteArrayInputStream(arr);
		return messageIs;
	}

	@Override
	public Object parse(InputStream stream) {
		Schema<ResponseMessageTransfer> respSchema = RuntimeSchema.getSchema(ResponseMessageTransfer.class);
		ResponseMessageTransfer responseMessageTransfer = respSchema.newMessage();
		try {
			ProtobufIOUtil.mergeFrom(stream, responseMessageTransfer, respSchema);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return responseMessageTransfer.getObject();
	}


}
