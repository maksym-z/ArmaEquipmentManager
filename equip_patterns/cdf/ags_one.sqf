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
[_unit] call bn_equip_cdf_rifleman_base;
_unit addBackpack 'RHS_AGS30_Gun_Bag';
_unit addItem '';
