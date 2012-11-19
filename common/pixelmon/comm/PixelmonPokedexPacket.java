package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagString;
import pixelmon.pokedex.Pokedex;

public class PixelmonPokedexPacket extends PixelmonPacket
{

	public NBTTagList data;
	
	public PixelmonPokedexPacket(NBTTagList nbt)
	{
		data = nbt;
	}
	
	public PixelmonPokedexPacket()
	{
		this(new NBTTagList());
	}
	
	public PixelmonPokedexPacket(NBTTagCompound nbt)
	{
		this(nbt.getTagList("Pokedex"));
		//NBTTagList nbtl = nbt.getTagList("Pokedex");
		//for(int i = 0; i < nbtl.tagCount(); i++)
			//data.add(nbtl.tagAt(i).getName());
	}
	
	public PixelmonPokedexPacket(Pokedex p)
	{
		this(p.writeToNBT(new NBTTagCompound()));
	}
	
	public Pokedex getPokedex(String username)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("Pokedex", data);
		Pokedex p = new Pokedex(username);
		p.readFromNBT(nbt);
		return p;
	}
	
	public void writePacketData(DataOutputStream d) throws IOException 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("Pokedex", data);
		//writeNBTTagCompound(nbt, d);
		d.writeInt(data.tagCount());
		for(int i = 0; i < data.tagCount(); i++)
		{
			String[] s = ((NBTTagString)data.tagAt(i)).data.split(":");
			int i1 = Integer.parseInt(s[0]);
			int i2 = Integer.parseInt(s[1]);
			d.writeInt(i1);
			d.writeInt(i2);
		}
	}

	public void readPacketData(DataInputStream d) throws IOException 
	{
		//data = readNBTTagCompound(d).getTagList("Pokedex");
		data = new NBTTagList();
		int size = d.readInt();
		for(int i = 0; i < size; i++)
			data.appendTag(new NBTTagString("", d.readInt() + ":" + d.readInt()));
	}

	public int getID() 
	{
		return EnumPackets.Pokedex.getIndex();
	}
	
}