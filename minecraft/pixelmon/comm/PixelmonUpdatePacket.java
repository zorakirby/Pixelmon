package pixelmon.comm;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import pixelmon.battles.status.StatusType;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGrowth;
import pixelmon.enums.EnumNature;
import pixelmon.enums.EnumType;

public class PixelmonUpdatePacket extends PixelmonDataPacket {
	public PixelmonUpdatePacket() {

	}

	EnumUpdateType[] updateTypes;

	public PixelmonUpdatePacket(NBTTagCompound p, EnumUpdateType[] updateTypes) {
		super(p, EnumPackets.UpdatePokemon);
		this.updateTypes = updateTypes;
	}

	public PixelmonUpdatePacket(EntityPixelmon p, EnumUpdateType[] updateTypes) {
		super(p, EnumPackets.UpdatePokemon);
		this.updateTypes = updateTypes;
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeInt(pokemonID);
		data.writeShort(updateTypes.length);
		for (EnumUpdateType t : updateTypes)
			data.writeByte(t.index);
		for (EnumUpdateType t : updateTypes) {
			switch (t) {
			case HP:
				data.writeShort(hp);
				data.writeShort(health);
				data.writeBoolean(isFainted);
				break;
			case Stats:
				data.writeShort(lvl);
				data.writeShort(nextLvlXP);
				data.writeShort(xp);
				data.writeShort(HP);
				data.writeShort(Speed);
				data.writeShort(Attack);
				data.writeShort(Defence);
				data.writeShort(SpecialAttack);
				data.writeShort(SpecialDefence);
				break;
			case Nickname:
				Packet.writeString(nickname, data);
				break;
			case Name:
				Packet.writeString(name, data);
				break;
			case Friendship:
				data.writeShort(friendship);
				break;
			case Moveset:
				data.writeShort(numMoves);
				for (int i = 0; i < numMoves; i++) {
					moveset[i].writeData(data);
				}
				break;
			case HeldItem:
				data.writeInt(heldItemId);
				break;
			case Status:
				data.writeShort(effectCount);
				for (int i = 0; i < effectCount; i++)
					data.writeShort(status.get(i).index);
				break;
			case CanLevel:
				data.writeBoolean(doesLevel);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void readPacketData(DataInput data) throws IOException {
		pokemonID = data.readInt();
		int numTypes = data.readShort();
		updateTypes = new EnumUpdateType[numTypes];
		for (int i = 0; i < numTypes; i++) {
			updateTypes[i] = EnumUpdateType.getType((int) data.readByte());
		}

		for (EnumUpdateType t : updateTypes) {
			switch (t) {
			case HP:
				hp = data.readShort();
				health = data.readShort();
				isFainted = data.readBoolean();
				break;
			case Stats:
				lvl = data.readShort();
				nextLvlXP = data.readShort();
				xp = data.readShort();
				HP = data.readShort();
				Speed = data.readShort();
				Attack = data.readShort();
				Defence = data.readShort();
				SpecialAttack = data.readShort();
				SpecialDefence = data.readShort();
				break;
			case Nickname:
				nickname = Packet.readString(data, 64);
				break;
			case Name:
				name = Packet.readString(data, 64);
				break;
			case Friendship:
				friendship = data.readShort();
				break;
			case Moveset:
				numMoves = data.readShort();
				for (int i = 0; i < numMoves; i++) {
					moveset[i] = new PixelmonMovesetDataPacket();
					moveset[i].readData(data);
				}
				break;
			case HeldItem:
				heldItemId = data.readInt();
				break;
			case Status:
				effectCount = data.readShort();
				for (int i = 0; i < effectCount; i++)
					status.add(StatusType.getEffect(data.readShort()));
				break;
			case CanLevel:
				doesLevel = data.readBoolean();
				break;
			default:
				break;
			}
		}
	}
}