package pixelmon.battles.attacks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pixelmon.battles.attacks.animations.AttackAnimationLeapForward;
import pixelmon.battles.attacks.animations.AttackAnimationParser;
import pixelmon.battles.attacks.animations.IAttackAnimation;
import pixelmon.enums.EnumType;

public class AttackBase {

	public int attackIndex;
	public String attackName;
	public String description;
	public EnumType attackType;

	public int attackCategory;
	public int basePower;
	public int ppMax;
	public int accuracy;

	boolean makesContact;
	boolean isHM;

	public ArrayList<EffectBase> effects = new ArrayList<EffectBase>();
	public ArrayList<IAttackAnimation> animations = new ArrayList<IAttackAnimation>();

	public AttackBase(int attackIndex, String moveName, ResultSet rs) throws SQLException {
		this.attackIndex = attackIndex;

		attackName = moveName;
		ppMax = rs.getInt("PPMAX");
		attackType = EnumType.parseTypeFromDBID(rs.getInt("TYPEID"));
		attackCategory = Attack.getAttackCategory(rs.getInt("MOVECATEGORYID"));
		basePower = rs.getInt("POWER");
		accuracy = rs.getInt("ACCURACY");
		if (rs.wasNull())
		accuracy = -1;
		isHM = rs.getInt("HMID") != -1;
		makesContact = rs.getBoolean("MAKESCONTACT");
		description = rs.getString("DESCRIPTION");

		String[] splits = rs.getString("EFFECT").split(";");
		for (String e : splits) {
			EffectBase etmp = EffectBase.getEffect(e);
			if (etmp != null)
				effects.add(etmp);
		}
		String animationString = rs.getString("ATTACKANIMATIONS");
		if (animationString == null) {
			animations = new ArrayList<IAttackAnimation>();
			animations.add(new AttackAnimationLeapForward());
		} else
			animations = AttackAnimationParser.GetAnimation(animationString);
	}

}
