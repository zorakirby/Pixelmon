package pixelmon.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import pixelmon.Pixelmon;
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
			ResultSet rs = stat.executeQuery("select * from MOVES where TMID IS NOT NULL ORDER BY TMID");
			while (rs.next()) {
				try {
					ItemTM item = new ItemTM(startId++, rs.getString("NAME"), rs.getInt("TMID"), EnumType.parseTypeFromDBID(rs.getInt("TYPEID")), false);
					Pixelmon.proxy.registerBossDropItem(item);
					TMs.add(item);
				} catch (Exception e) {
					System.out.println("Problem loading TMs");
				}
			}

		} catch (Exception e) {

		}
	}

	public static void addNames() {
		for (Item item : TMs) {
			LanguageRegistry.addName(item, ((ItemTM) item).getItemName());
		}
	}
}
