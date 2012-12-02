package pixelmon.battles.attacks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pixelmon.battles.attacks.animations.AttackAnimationLeapForward;
import pixelmon.battles.attacks.animations.AttackAnimationParser;
import pixelmon.battles.attacks.animations.IAttackAnimation;
import pixelmon.battles.attacks.attackEffects.EffectBase;
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
		ppMax = rs.getInt("PPMax");
		attackType = EnumType.parseType(rs.getString("Type"));
		attackCategory = Attack.getAttackCategory(rs.getString("Category"));
		basePower = rs.getInt("Power");
		accuracy = rs.getInt("Accuracy");
		isHM = rs.getInt("HMIndex") != -1;
		makesContact = rs.getInt("MakesContact") == 1;
		description = rs.getString("Description");

		String[] splits = rs.getString("Effect").split(";");
		for (String e : splits) {
			EffectBase etmp = EffectBase.getEffect(e);
			if (etmp != null)
				effects.add(etmp);
		}
		String animationString = rs.getString("AttackAnimations");
		if (animationString == null) {
			animations = new ArrayList<IAttackAnimation>();
			animations.add(new AttackAnimationLeapForward());
		} else
			animations = AttackAnimationParser.GetAnimation(animationString);
	}

}
