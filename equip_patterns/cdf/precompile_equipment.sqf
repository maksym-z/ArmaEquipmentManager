if (!isServer) exitWith {};
bn_equip_cdf_rifleman_rpk = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_rpk.sqf';
bn_equip_cdf_crewman = compile preprocessFileLineNumbers 'equip_patterns\cdf\crewman.sqf';
bn_equip_cdf_medical_backpack_ace = compile preprocessFileLineNumbers 'equip_patterns\cdf\medical_backpack_ace.sqf';
bn_equip_cdf_rifleman_spn_svd = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_spn_svd.sqf';
bn_equip_cdf_rifleman_spn_gp = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_spn_gp.sqf';
bn_equip_cdf_rifleman_pkm = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_pkm.sqf';
bn_equip_cdf_ags_three = compile preprocessFileLineNumbers 'equip_patterns\cdf\ags_three.sqf';
bn_equip_cdf_pkm = compile preprocessFileLineNumbers 'equip_patterns\cdf\pkm.sqf';
bn_equip_cdf_rifleman_base = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_base.sqf';
bn_equip_cdf_rifleman_medic = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_medic.sqf';
bn_equip_cdf_sl = compile preprocessFileLineNumbers 'equip_patterns\cdf\sl.sqf';
bn_equip_cdf_ags_two = compile preprocessFileLineNumbers 'equip_patterns\cdf\ags_two.sqf';
bn_equip_cdf_rpg7 = compile preprocessFileLineNumbers 'equip_patterns\cdf\rpg7.sqf';
bn_equip_cdf_rifleman_svd = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_svd.sqf';
bn_equip_cdf_rpg7_ammo = compile preprocessFileLineNumbers 'equip_patterns\cdf\rpg7_ammo.sqf';
bn_equip_cdf_rifleman_rpg7_ass = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_rpg7_ass.sqf';
bn_equip_cdf_svd = compile preprocessFileLineNumbers 'equip_patterns\cdf\svd.sqf';
bn_equip_cdf_spn_base = compile preprocessFileLineNumbers 'equip_patterns\cdf\spn_base.sqf';
bn_equip_cdf_assistant = compile preprocessFileLineNumbers 'equip_patterns\cdf\assistant.sqf';
bn_equip_cdf_gp = compile preprocessFileLineNumbers 'equip_patterns\cdf\gp.sqf';
bn_equip_cdf_pkm_ass = compile preprocessFileLineNumbers 'equip_patterns\cdf\pkm_ass.sqf';
bn_equip_cdf_rifleman_rpg7 = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_rpg7.sqf';
bn_equip_cdf_ak74 = compile preprocessFileLineNumbers 'equip_patterns\cdf\ak74.sqf';
bn_equip_cdf_rpk = compile preprocessFileLineNumbers 'equip_patterns\cdf\rpk.sqf';
bn_equip_cdf_rifleman_spn = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_spn.sqf';
bn_equip_cdf_rifleman_pkm_ass = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_pkm_ass.sqf';
bn_equip_cdf_ags_one = compile preprocessFileLineNumbers 'equip_patterns\cdf\ags_one.sqf';
bn_equip_cdf_rifleman = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman.sqf';
bn_equip_cdf_rifleman_spn_pkm = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_spn_pkm.sqf';
bn_equip_cdf_ace_med = compile preprocessFileLineNumbers 'equip_patterns\cdf\ace_med.sqf';
bn_equip_cdf_rifleman_spn_sl = compile preprocessFileLineNumbers 'equip_patterns\cdf\rifleman_spn_sl.sqf';
//Insert the following (uncommented) lines into description.sqf:
/*class CfgFunctions
{
	class bn
	{
		class myCategory
		{
			class campaign_cdf_preInit {
				file = "equip_patterns\cdf\precompile_equipment.sqf";
				preInit = 1;
			};
		};
	};
};*/