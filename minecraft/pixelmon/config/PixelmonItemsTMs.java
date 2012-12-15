package pixelmon.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import pixelmon.database.DatabaseHelper;
import pixelmon.database.Move;
import pixelmon.enums.EnumType;
import pixelmon.items.ItemTM;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelmonItemsTMs {
	public static ArrayList<Item> TMs = new ArrayList<Item>();

	public static void load(Configuration cfg) {
		System.out.println("PIXELMON: Loading TM/HMs");
		int startId = cfg.get("IDs", "TM Index Start", 11000).getInt();
		try {
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Moves where TMIndex!=-1 ORDER BY TMIndex");
			ArrayList<Move> moves = new ArrayList<Move>();
			while (rs.next()) {
				try {
					ItemTM item = new ItemTM(startId++, rs.getString("Name"), rs.getInt("TMIndex"), EnumType.parseType(rs.getString("Type")), false);
					TMs.add(item);
				} catch (Exception e) {
					System.out.println("Problem loading TMs");
				}
			}

			conn.close();
		} catch (Exception e) {

		}
	}

	public static void addNames() {
		for (Item item : TMs) {
			LanguageRegistry.addName(item, ((ItemTM) item).getItemName());
		}
	}
}
