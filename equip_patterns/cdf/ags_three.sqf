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
_unit addBackpack 'rhs_sidor';
for '_i' from 1 to 2 do {_unit addItemToBackpack 'RHS_mag_VOG30_30';};
