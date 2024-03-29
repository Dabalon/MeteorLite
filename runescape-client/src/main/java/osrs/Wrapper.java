package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;

@ObfuscatedName("ij")
@Implements("Wrapper")
public abstract class Wrapper extends DualNode {
	@ObfuscatedName("c")
	@Export("size")
	final int size;

	Wrapper(int var1) {
		this.size = var1;
	}

	@ObfuscatedName("n")
	@Export("get")
	abstract Object get();

	@ObfuscatedName("c")
	@Export("isSoft")
	abstract boolean isSoft();
}
