if (!isServer) exitWith {};
_unit = _this select 0;

removeAllWeapons _unit;
removeAllItems _unit;
removeAllAssignedItems _unit;
removeUniform _unit;
removeVest _unit;
removeBackpack _unit;
removeHeadgear _unit;
removeGoggles _unit;
[_unit] call bn_equip_test_rifleman;// kit
[_unit] call bn_equip_test_map_and_stuff;// kit
