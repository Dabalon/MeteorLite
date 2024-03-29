package net.runelite.rs.api;

import net.runelite.api.Buffer;
import net.runelite.mapping.Import;

public interface RSBuffer extends Buffer, RSNode {

  @Import("array")
  byte[] getPayload();

  @Import("offset")
  int getOffset();

  @Import("writeByte")
  @Override
  void writeByte$api(int var1);

  @Import("writeShort")
  @Override
  void writeShort$api(int var1);

  @Import("writeMedium")
  @Override
  void writeMedium$api(int var1);

  @Import("writeInt")
  @Override
  void writeInt$api(int var1);

  @Import("writeLong")
  @Override
  void writeLong$api(long var1);

  @Import("writeStringCp1252NullTerminated")
  @Override
  void writeStringCp1252NullTerminated$api(String string);
}