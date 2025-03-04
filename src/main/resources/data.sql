-- タスク初期データ（既に同じタスク番号が存在する場合は挿入されない）
INSERT IGNORE INTO tasks (task_no, task_name, registration_date, registrant, detail, task_status)
VALUES
('T001', 'タスク1', NOW(), '登録者1', 'タスク1の詳細', '未完了'),
('T002', 'タスク2', NOW(), '登録者2', 'タスク2の詳細', '完了'),
('T003', 'タスク3', NOW(), '登録者3', 'タスク3の詳細', '未完了'),
('T004', 'タスク4', NOW(), '登録者4', 'タスク4の詳細', '完了'),
('T005', 'タスク5', NOW(), '登録者5', 'タスク5の詳細', '未完了');

-- 社員情報初期データ
-- タスク T001 の社員情報
INSERT IGNORE INTO employee_info (task_no, employee_department, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T001', '営業部', 'JA', 1, '山田太郎', '未回答', ''),
('T001', '技術部', 'JB', 2, '鈴木一郎', '未回答', ''),
('T001', '総務部', 'JC', 3, '佐藤花子', '未回答', ''),
('T001', '経理部', 'JD', 4, '高橋次郎', '未回答', '');

-- タスク T002 の社員情報
INSERT IGNORE INTO employee_info (task_no, employee_department, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T002', '営業部', 'JA', 1, '山田太郎', '未回答', ''),
('T002', '技術部', 'JB', 2, '鈴木一郎', '未回答', ''),
('T002', '総務部', 'JC', 3, '佐藤花子', '未回答', ''),
('T002', '経理部', 'JD', 4, '高橋次郎', '未回答', '');

-- タスク T003 の社員情報
INSERT IGNORE INTO employee_info (task_no, employee_department, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T003', '営業部', 'JA', 1, '山田太郎', '未回答', ''),
('T003', '技術部', 'JB', 2, '鈴木一郎', '未回答', ''),
('T003', '総務部', 'JC', 3, '佐藤花子', '未回答', ''),
('T003', '経理部', 'JD', 4, '高橋次郎', '未回答', '');

-- タスク T004 の社員情報
INSERT IGNORE INTO employee_info (task_no, employee_department, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T004', '営業部', 'JA', 1, '山田太郎', '未回答', ''),
('T004', '技術部', 'JB', 2, '鈴木一郎', '未回答', ''),
('T004', '総務部', 'JC', 3, '佐藤花子', '未回答', ''),
('T004', '経理部', 'JD', 4, '高橋次郎', '未回答', '');

-- タスク T005 の社員情報
INSERT IGNORE INTO employee_info (task_no, employee_department, employee_project, employee_number, employee_name, answer_status, answer_remark)
VALUES
('T005', '営業部', 'JA', 1, '山田太郎', '未回答', ''),
('T005', '技術部', 'JB', 2, '鈴木一郎', '未回答', ''),
('T005', '総務部', 'JC', 3, '佐藤花子', '未回答', ''),
('T005', '経理部', 'JD', 4, '高橋次郎', '未回答', '');
