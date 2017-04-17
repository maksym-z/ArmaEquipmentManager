if (!isServer) exitWith {};
bn_equip_test_rifleman = compile preprocessFileLineNumbers 'equip_patterns\test\rifleman.sqf';
bn_equip_test_teamleader = compile preprocessFileLineNumbers 'equip_patterns\test\teamleader.sqf';
//Insert the following (uncommented) lines into description.sqf:
/*class CfgFunctions
{
	class bn
	{
		class myCategory
		{
			class campaign_test_preInit {
				file = "equip_patterns\test\precompile_equipment.sqf";
				preInit = 1;
			};
		};
	};
};*/