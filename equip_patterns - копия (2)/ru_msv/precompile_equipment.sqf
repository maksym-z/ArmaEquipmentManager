if (!isServer) exitWith {};bn_equip_ru_msv_ace_med = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\ace_med.sqf';
bn_equip_ru_msv_ak74m = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\ak74m.sqf';
bn_equip_ru_msv_ak74m_ammo = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\ak74m_ammo.sqf';
bn_equip_ru_msv_assistant = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\assistant.sqf';
bn_equip_ru_msv_gp = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\gp.sqf';
bn_equip_ru_msv_leader = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\leader.sqf';
bn_equip_ru_msv_pkm = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\pkm.sqf';
bn_equip_ru_msv_pkm_ass = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\pkm_ass.sqf';
bn_equip_ru_msv_soldier_base = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\soldier_base.sqf';
bn_equip_ru_msv_rifleman = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman.sqf';
bn_equip_ru_msv_ak74 = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\ak74.sqf';
bn_equip_ru_msv_rifleman_assistant = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_assistant.sqf';
bn_equip_ru_msv_rifleman_gp = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_gp.sqf';
bn_equip_ru_msv_rifleman_pkm = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_pkm.sqf';
bn_equip_ru_msv_rifleman_pkm_ass = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_pkm_ass.sqf';
bn_equip_ru_msv_rifleman_rpg7 = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_rpg7.sqf';
bn_equip_ru_msv_rpg7 = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rpg7.sqf';
bn_equip_ru_msv_rifleman_rpg7_ass = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_rpg7_ass.sqf';
bn_equip_ru_msv_rpg7_ammo = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rpg7_ammo.sqf';
bn_equip_ru_msv_rifleman_rpk = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rifleman_rpk.sqf';
bn_equip_ru_msv_rpk = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\rpk.sqf';
bn_equip_ru_msv_squad_leader = compile preprocessFileLineNumbers 'equip_patterns\ru_msv\squad_leader.sqf';
//Insert the following (uncommented) lines into description.sqf:
/*class CfgFunctions
{
	class bn
	{
		class myCategory
		{
			class campaign_ru_msv_preInit {
				file = 'equip_patterns\ru_msv\precompile_equipment.sqf';
				preInit = 1;
			};
		};
	};
};*//